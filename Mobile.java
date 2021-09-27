interface Mobile extends Positioned
{

    public void cXVel(double in);
    public void cYVel(double in);
    public void cX(double in);
    public void cY(double in);

    public double getXVel();
    public double getYVel();
    public double getDecayFactor();

    public void setXVel(double in);
    public void setYVel(double in);
    public void setDecayFactor(double in);


}
