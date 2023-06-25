public enum DrawEnum{
    //CASE POINT
    WAIT_POINT(1),  
    //CASE LINE
    WAIT_BEGIN_LINE(2),
    WAIT_END_LINE(3),
    //CASE SQUARE
    WAIT_BEGIN_SQUARE(4),
    WAIT_END_SQUARE(5),
    //CASE RECTANGLE
    WAIT_BEGIN_RECTANGLE(6),
    WAIT_END_RECTANGLE(7),
    //CASE CIRCLE
    WAIT_BEGIN_CIRCLE(8),
    WAIT_END_CIRCLE(9),
    //CASE ELLIPSE
    WAIT_BEGIN_ELLIPSE(10),
    WAIT_END_ELLIPSE(11);


    public final int action;

    DrawEnum(int action){
        this.action = action;
    }

    public int getActionValue(){
        return action;
    }
    

    public static DrawEnum getAction(int action){
        for(DrawEnum drwEnum : DrawEnum.values()){
            if(action == drwEnum.getActionValue()){
                return drwEnum;
            }
        }
        return null;
    }
    
}