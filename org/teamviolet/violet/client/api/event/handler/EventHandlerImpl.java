//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.event.handler;

import java.util.concurrent.*;
import java.lang.annotation.*;
import java.util.stream.*;
import java.util.*;
import java.lang.reflect.*;

public class EventHandlerImpl implements EventHandler
{
    private final Map<Class<?>, Map<Object, List<Method>>> subscriptionMap;
    private final Map<Class<?>, Map<Class<?>, List<Method>>> syntheticSubscriptionMap;
    private final List<Object> registeredObjectList;
    private final List<Class<?>> registeredClassList;
    private final List<EventHandler> attachedHandlerList;
    
    public EventHandlerImpl() {
        this.subscriptionMap = new ConcurrentHashMap<Class<?>, Map<Object, List<Method>>>();
        this.syntheticSubscriptionMap = new ConcurrentHashMap<Class<?>, Map<Class<?>, List<Method>>>();
        this.registeredObjectList = new ArrayList<Object>();
        this.registeredClassList = new ArrayList<Class<?>>();
        this.attachedHandlerList = new ArrayList<EventHandler>();
    }
    
    public void register(final Object object) {
        if (!this.registeredObjectList.contains(object)) {
            final Map<Class<?>, List<Method>> methodListMap = new ConcurrentHashMap<Class<?>, List<Method>>();
            for (final Method method2 : object.getClass().getDeclaredMethods()) {
                if (method2.isAnnotationPresent(Listener.class) && method2.getParameterCount() == 1 && method2.getReturnType().isAssignableFrom(Void.TYPE) && !Modifier.isStatic(method2.getModifiers())) {
                    methodListMap.putIfAbsent(method2.getParameterTypes()[0], new ArrayList<Method>());
                    method2.setAccessible(true);
                    methodListMap.get(method2.getParameterTypes()[0]).add(method2);
                }
            }
            for (final Map.Entry<Class<?>, List<Method>> entry : methodListMap.entrySet()) {
                this.subscriptionMap.putIfAbsent(entry.getKey(), new ConcurrentHashMap<Object, List<Method>>());
                entry.setValue(entry.getValue().stream().sorted(Comparator.comparing(method -> -method.getDeclaredAnnotation(Listener.class).priority())).collect((Collector<? super Object, ?, List<Method>>)Collectors.toList()));
                this.subscriptionMap.get(entry.getKey()).put(object, entry.getValue());
            }
            this.registeredObjectList.add(object);
        }
    }
    
    public void unregister(final Object object) {
        if (this.registeredObjectList.contains(object)) {
            for (final Class<?> clazz : this.subscriptionMap.keySet()) {
                this.subscriptionMap.get(clazz).remove(object);
            }
            this.registeredObjectList.remove(object);
        }
    }
    
    public void register(final Class<?> clazz) {
        if (!this.registeredClassList.contains(clazz)) {
            final Map<Class<?>, List<Method>> methodListMap = new ConcurrentHashMap<Class<?>, List<Method>>();
            for (final Method method2 : clazz.getDeclaredMethods()) {
                if (method2.isAnnotationPresent(Listener.class) && method2.getParameterCount() == 1 && method2.getReturnType().isAssignableFrom(Void.TYPE) && Modifier.isStatic(method2.getModifiers())) {
                    methodListMap.putIfAbsent(method2.getParameterTypes()[0], new ArrayList<Method>());
                    method2.setAccessible(true);
                    methodListMap.get(method2.getParameterTypes()[0]).add(method2);
                }
            }
            for (final Map.Entry<Class<?>, List<Method>> entry : methodListMap.entrySet()) {
                this.syntheticSubscriptionMap.putIfAbsent(entry.getKey(), new ConcurrentHashMap<Class<?>, List<Method>>());
                entry.setValue(entry.getValue().stream().sorted(Comparator.comparing(method -> -method.getDeclaredAnnotation(Listener.class).priority())).collect((Collector<? super Object, ?, List<Method>>)Collectors.toList()));
                this.syntheticSubscriptionMap.get(entry.getKey()).put(clazz, entry.getValue());
            }
            this.registeredClassList.add(clazz);
        }
    }
    
    public void unregister(final Class<?> clazz) {
    }
    
    public boolean isRegistered(final Object object) {
        return this.registeredObjectList.contains(object);
    }
    
    public boolean isRegistered(final Class<?> clazz) {
        return false;
    }
    
    public void attach(final EventHandler handler) {
        if (!this.attachedHandlerList.contains(handler)) {
            this.attachedHandlerList.add(handler);
        }
    }
    
    public void unattach(final EventHandler handler) {
        this.attachedHandlerList.remove(handler);
    }
    
    public boolean isAttached(final EventHandler handler) {
        return this.attachedHandlerList.contains(handler);
    }
    
    public <T> void post(final T object) {
        if (this.subscriptionMap.containsKey(object.getClass())) {
            for (final Map.Entry<Object, List<Method>> entry : this.subscriptionMap.get(object.getClass()).entrySet()) {
                for (final Method method : entry.getValue()) {
                    try {
                        method.invoke(entry.getKey(), object);
                    }
                    catch (IllegalAccessException | InvocationTargetException ex3) {
                        final ReflectiveOperationException ex;
                        final ReflectiveOperationException e = ex;
                        e.printStackTrace();
                    }
                }
            }
        }
        if (this.syntheticSubscriptionMap.containsKey(object.getClass())) {
            for (final Map.Entry<Class<?>, List<Method>> entry2 : this.syntheticSubscriptionMap.get(object.getClass()).entrySet()) {
                for (final Method method : entry2.getValue()) {
                    try {
                        method.invoke(null, object);
                    }
                    catch (IllegalAccessException | InvocationTargetException ex4) {
                        final ReflectiveOperationException ex2;
                        final ReflectiveOperationException e = ex2;
                        e.printStackTrace();
                    }
                }
            }
        }
        if (!this.attachedHandlerList.isEmpty()) {
            this.attachedHandlerList.forEach(handler -> handler.post((Object)object));
        }
    }
}
