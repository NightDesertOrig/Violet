#version 120

uniform sampler2D texture;
uniform vec2 texelSize;
uniform float radius;
uniform float divider;
uniform float maxSample;

uniform vec2 resolution;
uniform float time;

uniform vec4 insideColor;
uniform int insideMode;

uniform vec4 outsideColor;
uniform int outsideMode;

uniform int glow;

// Basic Color
vec4 simple(vec4 color) {
    return color;
}

// Matter
vec4 matter(vec4 color) {
    vec3 tint = vec3(0.1,.9,.4);
    vec3 bgColor = vec3(1,0,1);

    #define iterations 14
    #define formuparam 0.530
    #define time1 cos(time*.2)
    #define volsteps 18
    #define stepsize 0.2

    #define zoom   0.800
    #define tile   0.850
    #define speed  0.00001

    #define brightness 0.00045
    #define darkmatter 0.400
    #define distfading 0.760
    #define saturation 0.800

    float zoomFactor = .1;

    //get coords and direction
    vec2 uv=gl_FragCoord.xy/resolution.xy;
    uv.y*=resolution.y/resolution.x;
    vec3 dir=vec3(uv*zoom,tan(.10)*1000.0);

    float a2=speed+.6;
    float a1=0.0;
    mat2 rot1=mat2(cos(a1),tan(a1),-sin(a1),cos(a1));
    mat2 rot2=rot1;//mat2(cos(a2),sin(a2),-sin(a2),cos(a2));
    dir.xz*=rot1;
    dir.xy*=rot2;

    //from.x-=time;
    //mouse movement*time
    vec3 from=vec3(9.,0.,0.);
    from+=vec3((tan(.15),.152,-2.));

    from.x-=100.;
    from.y-=100.;

    from.xz*=rot1;
    from.xy*=rot2;

    //volumetric rendering
    float s=.4,fade=.2;
    vec3 v=vec3(0.9);
    for (int r=0; r<volsteps; r++) {
        vec3 p=from+s*dir*.5;
        p = abs(vec3(tile)-mod(p,vec3(tile*2.))); // tiling fold
        float pa,a=pa=16.;
        for (int i=0; i<iterations; i++) {
            p=abs(p)/dot(p,p)-formuparam*(abs(sin(time1 / 5.0)) + .6); // the magic formula
            a+=abs(length(p)-pa); // absolute sum of average change
            pa=length(p);
        }
        float dm=max(0.,darkmatter-a*a*tan(.9)); //dark matter
        a*=a*a*2.; // add contrast
        if (r>3) fade*=1.-dm; // dark matter, don't render near
        //v+=vec3(dm,dm*.5,0.);
        v+=fade;
        v+=vec3(s*s,s*s*s,s*s*s*s)*a*brightness*fade; // coloring based on distance
        fade*=distfading/1.2; // distance fading
        s+=stepsize;
    }
    v=mix(vec3(length(v)),v,saturation); //color adjust
    return vec4(v*.01,color.a);
}

// Pink Galaxy
float field(in vec3 p, float s, int idx) {
    float strength = 25. + .1 * log(2.e-6 + fract(sin(time) * 4373.11));
    float accum = s/2.;
    float prev = 0.;
    float tw = 0.;
    for (int i = 0; i < 26; ++i) {
        float mag = dot(p, p);
        p = abs(p) / mag + vec3(-.6, -.2, -1.1);
        float w = exp(-float(i) / 4.);
        accum += w * exp(-strength * pow(abs(mag - prev), 3.2));
        tw += w;
        prev = mag;
    }
    return max(0., 5.6 * accum / tw - .6);
}


vec3 nrand3(vec2 co) {
    vec3 a = fract( cos( co.x*4.3e-3 + co.y )*vec3(1.3e5, 4.7e5, 2.9e5) );
    vec3 b = fract( sin( co.x*0.3e-3 + co.y )*vec3(8.1e5, 1.0e5, 0.1e5) );
    vec3 c = mix(a, b, .9);
    return c;
}

vec4 pinkGalaxy(vec4 color) {
    vec2 uv = 2. * gl_FragCoord.xy / resolution.xy - 1.;
    vec2 uvs = uv * resolution.xy / max(resolution.x, resolution.y);
    vec3 p = vec3(uvs / 4., 0) + vec3(1., -1.3, 0.);
    p += .2 * vec3(sin(time / 999.), sin(time / 9999999.), sin(time / 9999999.));

    float freqs[4];
    freqs[0] = 0.15;
    freqs[1] = 0.0;
    freqs[2] = 0.4;
    freqs[3] = 0.7;

    float t = field(p, freqs[3],126);
    float v = (1. - exp((abs(uv.x) - 1.) * 6.)) * (1. - exp((abs(uv.y) - 1.) * 6.));

    vec3 p2 = vec3(uvs / (4.+sin(time*.11)*0.2+0.2+sin(time*0.15)*0.3+0.1), 1.5) + vec3(2., -1.3, -1.);
    p2 += 0.1 * vec3(sin(time / 99999999999.), sin(time / 99999999999.), sin(time / 9999999999999.)); //speed
    float t2 = field(p2, freqs[3], 18);
    vec4 c2 =  mix(.1, .2, v) * vec4(1.3 * t2 * t2 * t2, 1.8  * t2 * t2, t2* freqs[0], t2);

    vec2 seed = p.xy;
    seed = floor(seed * resolution.x);
    vec3 rnd = nrand3(seed);
    vec4 starcolor = vec4(pow(rnd.y, 50.0));

    vec2 seed2 = p2.xy * 3.0;
    seed2 = floor(seed2 * resolution.x);
    vec3 rnd2 = nrand3(seed2);
    starcolor += vec4(pow(rnd2.y, 400.0));

    vec3 backclr = vec3(0.1,0,0);

    return vec4(mix(freqs[3]-.5, 1., 3.0) * vec4(1.5*freqs[2] * t + backclr.r * t* t, .2*freqs[1] * t + backclr.g * t, freqs[3]*t + backclr.b, 1.0) +c2+starcolor);
}

vec4 deepSpace(vec4 color) {
    float time_ = (time+10.) * 30.0;
    vec2 uv = (-resolution.xy + 2.0 * gl_FragCoord.xy ) / resolution;
    float s = 0.0;
    float v = -0.010;
    float t = time_*0.005;
    uv.x += sin(t) * 0.15;
    //uv.y += cos(t) * 0.15;
    //float si = sin(t + 2.17);
    //float co = cos(t);
    //uv *= mat2(co, si, -si, co);
    vec3 col_ = vec3(0.0);
    vec3 init = vec3(-0.25, -0.25 + sin(time_ * 0.001) * 0.4, floor(time_) * 0.0008);
    for (int r = 0; r < 100; r++)
    {
        vec3 p = init + s * vec3(uv, 0.143);
        p.z = mod(-p.z, 2.0)-1.0;
        for (int i=0; i < 10; i++)
        {
            p = abs(p * 2.003) / dot(p, p) - 0.75;
        }
        v += length(p * p) * smoothstep(0.0, 0.1, 0.9 - s) * .0005;
        // Get a purple and cyan effect by biasing the RGB in different ways...
        //col_ +=  vec3(v * 0.8, 1.1 - s * 0.5, .7 + v * 1.5) * v * 0.016;
        col_+=vec3(v*0.02);
        s += .005;
    }
    return vec4(col_,color.a);
}

// By 9k!
void main(void) {
    vec4 centerCol = texture2D(texture, gl_TexCoord[0].xy);
    vec4 color = vec4(1,1,1,1);

    if(centerCol.a != 0) {
        if (insideMode == 0) {
            color = simple(insideColor);
        }

        if (insideMode == 1) {
            color = matter(insideColor);
        }

        if (insideMode == 2) {
            color = pinkGalaxy(insideColor);
        }

        if (insideMode == 3) {
            color = deepSpace(insideColor);
        }

        gl_FragColor = vec4(color.rgb, insideColor.a);
    } else {
        float alpha = 0;

        for (float x = -radius; x <= radius; x++) {
            for (float y = -radius; y <= radius; y++) {
                vec4 currentColor = texture2D(texture, gl_TexCoord[0].xy + vec2(texelSize.x * x, texelSize.y * y));
                if (currentColor.a != 0) {
                    if (glow==0) alpha = 1;
                    else alpha += divider > 0 ? max(0, (maxSample - distance(vec2(x, y), vec2(0))) / divider) : 1;

                    if (outsideMode == 0) {
                        color = simple(outsideColor);
                    }

                    if (outsideMode == 1) {
                        color = matter(outsideColor);
                    }

                    if (outsideMode == 2) {
                        color = pinkGalaxy(outsideColor);
                    }

                    if (outsideMode == 3) {
                        color = deepSpace(outsideColor);
                    }
                }
            }
        }

        gl_FragColor = vec4(color.rgb, alpha);
    }
}