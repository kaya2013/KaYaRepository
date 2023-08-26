package at.guigu.bootInitialzr.bean;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PConverter implements IPConverter{

    private final VxsPosition vxsPosition;
    private final Position position;

    @Override
    public List<Entity> getEntities( ){
        List<Entity> entities = new ArrayList<>();
        entities.add(new Entity<>(vxsPosition, VxsPosition::getVxsID, position, Position::setId,    val -> val * val));
        entities.add(new Entity<>(vxsPosition, VxsPosition::getDesc,  position, Position::setDesc,  val->val + val));
        entities.add(new Entity<>(vxsPosition, VxsPosition::getPrice, position, Position::setPrice, val->val * val));
        entities.add(new Entity<>(vxsPosition, VxsPosition::getDate,  position, Position::setDate,  val->val));
        return entities;
    }

    @Override
    public void execute() {
        getEntities( ).forEach(Entity::toTo);
    }
}
