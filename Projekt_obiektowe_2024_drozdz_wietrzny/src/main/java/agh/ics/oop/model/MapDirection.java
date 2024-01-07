package agh.ics.oop.model;

public enum MapDirection {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    public String toString(){
        return switch(this){
            case NORTH -> "Północ";
            case NORTHEAST -> "Północny-Wschód";
            case EAST -> "Wschód";
            case SOUTHEAST -> "Południowy-Wschód";
            case SOUTH -> "Południe";
            case SOUTHWEST -> "Południowy-Zachód";
            case WEST -> "Zachód";
            case NORTHWEST -> "Północy-Zachód";
        };
    }

    public MapDirection next(){
       MapDirection[] arr = MapDirection.values();
       int index = (this.ordinal() + 1) % arr.length;
       return MapDirection.valueOf(arr[index].name());
    }
    public MapDirection previous(){
        MapDirection[] arr = MapDirection.values();
        int index = (this.ordinal() + arr.length -1) % arr.length;
        return MapDirection.valueOf(arr[index].name());
    }
    public MapDirection rotate(int rotations){
        MapDirection[] arr = MapDirection.values();
        int index = (this.ordinal() + rotations) % arr.length;
        return MapDirection.valueOf(arr[index].name());
    }

    public MapDirection opposite(){
        MapDirection[] arr = MapDirection.values();
        return this.rotate(arr.length/2);
    }

    public Vector2d toUnitVector(){
        return switch(this){
            case NORTH -> new Vector2d(0,1);
            case NORTHEAST -> new Vector2d(1,1);
            case EAST -> new Vector2d(1,0);
            case SOUTHEAST -> new Vector2d(1,-1);
            case SOUTH -> new Vector2d(0,-1);
            case SOUTHWEST -> new Vector2d(-1,-1);
            case WEST -> new Vector2d(-1,0);
            case NORTHWEST -> new Vector2d(-1,1);
        };
    }
}