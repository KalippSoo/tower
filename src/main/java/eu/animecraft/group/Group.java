package eu.animecraft.group;

import eu.animecraft.data.components.Utils;

import java.util.List;

public class Group {
    private String group;
    private String prefix;
    private String suffix;
    private List<String> perms;
    private boolean staff;

    public Group(String group, String prefix, String suffix, boolean staff, List<String> perms) {
        this.setGroup(group);
        this.setPrefix(prefix);
        this.setSuffix(suffix);
        this.perms = perms;
        this.staff = staff;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPrefix() {
        return Utils.color(this.prefix);
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setPerms(List<String> perms) {
        this.perms = perms;
    }

    public List<String> getPerms() {
        return this.perms;
    }

    public boolean isStaff() {
        return this.staff;
    }

    public void setStaff(boolean staff) {
        this.staff = staff;
    }
}
