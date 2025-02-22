package eu.animecraft.data;

public class StatsReroll {

    public double
    a,
    b,
    c;

    public StatsReroll(double... stats){
        this.a = stats[0];
        this.b = stats[1];
        this.c = stats[2];
    }

    public String d(int i){

        double t = 0;
        if (i == 0) t=a;
        if (i == 1) t=b;
        if (i == 2) t=c;
        if (t>=20) return " &bSSS";
        if (t>=17.5) return " &bSS";
        if (t>=15) return " &bS+";
        if (t>=12.5) return " &bS";
        if (t>=10) return " &bS-";
        if (t>=7.5) return " &aA+";
        if (t>=5) return " &aA";
        if (t>=2.5) return " &aA-";
        if (t>=0) return " &eB+";
        if (t<=-12.5) return " &4D+";
        if (t<=-10) return " &cC-";
        if (t<=-7.5) return " &cC";
        if (t<=-5)return " &eC+";
        if (t<=-2.5)return " &eB-";
        if (t<0)return " &eB";
        return "";
        
    }

}
