public enum DrawEnum(){
    //CASE POINT
    WAIT_POINT(false),
    //CASE LINE
    WAIT_BEGIN_LINE(false),
    WAIT_END_LINE(false),
    //CASE SQUARE
    WAIT_BEGIN_SQUARE(false),
    WAIT_END_SQUARE(false),
    //CASE RECTANGLE
    WAIT_BEGIN_RECTANGLE(false),
    WAIT_END_RECTANGLE(false);

    public final boolean active;

    DrawEnum(boolean active){
        this.active = active;
    }

    public boolean getActiveValue(){
        return active;
    }

    public void setActiveValue(boolean active){
        this.active = active;
    }
    
}