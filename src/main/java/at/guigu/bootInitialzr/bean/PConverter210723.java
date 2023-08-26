package at.guigu.bootInitialzr.bean;


import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class PConverter210723 implements IPConverter{
    private VxsPosition vxsPosition;
    private Position position;

    @Override
    public List<Entity> getEntities( ){
        List<Entity> entities = new ArrayList<>();
        entities.add(new Entity<>(VxsPosition::getVxsID,    Position::setId));
        entities.add(new Entity<>(VxsPosition::getDesc,     Position::setDesc));
        entities.add(new Entity<>(VxsPosition::getPrice,    Position::setPrice));
        entities.add(new Entity<>(VxsPosition::getDate,     Position::setDate));
        return entities;
    }

    @Override
    public void execute() {
        getEntities( ).forEach(entity -> {
            entity.source = vxsPosition;
            entity.target = position;
            entity.toTo();
        });
    }
}
