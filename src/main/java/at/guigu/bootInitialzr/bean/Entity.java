package at.guigu.bootInitialzr.bean;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class Entity<SO, TO, T> {
    SO source;

    TO target;
    Function<SO, T> getter;
    BiConsumer<TO, T> setter;

    Function<T, T> regulator;

    public Entity(SO source, Function<SO, T> getter, TO target, BiConsumer<TO, T> setter, Function<T, T>  regulator) {
        this.source = source;
        this.target = target;
        this.getter = getter;
        this.setter = setter;
        this.regulator = regulator;
    }

    public Entity(Function<SO, T> getter, BiConsumer<TO, T> setter) {
        this.getter = getter;
        this.setter = setter;
    }

    public void toTo() {
        T t = getter.apply(source);
        if(t!=null && regulator != null){
            setter.accept(target, regulator.apply(t));
        }
    }
}
