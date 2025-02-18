package eu.animecraft.tower.towers;

import eu.animecraft.tower.Tower;
import eu.animecraft.tower.tools.DamageType;
import eu.animecraft.tower.tools.Rarity;

import java.util.UUID;

public class TowerSonGoku extends Tower {
    public TowerSonGoku() {
        super(1, Rarity.COMMON, "Goko", null, false, 5, UUID.randomUUID(),
                "ewogICJ0aW1lc3RhbXAiIDogMTcwMTkzMDkxNTM3MSwKICAicHJvZmlsZUlkIiA6ICI4NTZkNmYzMDgzYWY" +
                        "0MDNjODUyMTRlMGQ3NzFhY2ExNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJLb29yeEF0aWxsYSIsCi" +
                        "AgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIi" +
                        "A6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS" +
                        "85YmMyZmFhOTJlMWMzOTM4MTU5ZDUzYWI5MDhhZWJjMjY4NTg1YzBjZWE1MzAzOTlmOWE2YWQ4ODd" +
                        "lNTAyNTA4IgogICAgfQogIH0KfQ==",
                "huWy6O7VaHvXRE4re0XrDYXBd9iAg2fqaQ/0sFCuskvRyP7wmeXBI/9RfQ5xlPE8atNO//ui0xMjVudaOPJgbeUr1eh6WjW6" +
                        "EybIf261Y3TungKRTff2DDOFYG3FW3lOx8947QamTOeBG5fVxdQK3yYluyuWpPQsK9IPHrIee2UQ0OGYcCiel6Dc" +
                        "ONAHaehTzUvub7Q/iKFPhFjIvLCVtXc6DvJDqJWjqpHWZtOVP3ktsWtE6U1CJOCdEAY2w+kKzsKKdf+uZU6OABl+" +
                        "Jnnnff+Jx+leqGfsxAXkDjSyYgMQ1nLuMBAejH/wqE3NbrsEbUYPJC7iFkq0QtLtir685rnhxMm213ZYO9iA6Du4" +
                        "zw7IX+8IV3xrcZNtuY6blx3KuTLNkHbBSIsws2wDCQs1gZj8BodduS+sadG1HkoatwIByeGIQfhjkLIgHoh4xYPn" +
                        "rUU4SXlX4g9l0RG1bSXHcv7S6MMWGOuCkylIifqs4yVnrR8r+c/IpLyGdYzqQzs59yx5uG/MwqQE28Rw4+IXvLvI" +
                        "UFAJOh6lDD21axsnctCkdiBYEk4SVqGZhMJuGcYy/V6RXS6SjP6PWFxhDGnP+Y5npD6mvS79op7Dts/Ri4e1Fv1j" +
                        "yJkzhZIeVovghkWGgvmIeI8ccm85szZiAgyHQ+mt2a/A8dji9XAo7odeH+U=",
                "ewogICJ0aW1lc3RhbXAiIDogMTY5NDEwNDc4NzcxOCwKICAicHJvZmlsZUlkIiA6ICI4ZGUyNDAzYTEyMjU0ZmFkOTM1OTYx" +
                        "YWFlYmQwNGUyOSIsCiAgInByb2ZpbGVOYW1lIiA6ICJZdW5hbWkyNyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6" +
                        "IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1" +
                        "cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83ZjllZWM2Zjc5MTk4N2NlNWQzNDQxZDZlYTdiMjk1YjY5NDVkZTE2" +
                        "ZjlhNTMzOTZiN2M5ZWJmMTVmMjY2MTZjIgogICAgfQogIH0KfQ==",
                "jqQ8daiNdyYHcqEPgiK6UYvCSkHoDFCH0GgH9Yexe9KPsl0YHPs/4f9zLAuXNYkDmSwG7sSmYr9kITLaHfcXGMAtjwOPPUVe" +
                        "ArTz/LmJVysL1C13+fTKwFXqiu34YJUYE/gK4zZ4kIFiD80l0/QMtJPE66tSO81W+AGup7OwhQMA15H2MB46x7Do" +
                        "t9wiRbuzhJDyF0QWO9cem+dNfvLft9IjEANpbIHLBq5t/tsRi8uQbCwVilNn3Oe+7K2CnDlg0Vo1u2Jpe9qS1LI1" +
                        "9AtQh9q8vWQksPp6mp/VSJelM4M4WBBZJURfhA37iK1ivwTMFnlPuHZFhcTiiyHViQDPT/IPOSJ3vPqYde6mXDeV" +
                        "MYnQX39XOMbhEnrjrfO8LH8U9fAxTv4HF9D85JqQCxmE7pNEKrb3oPIOfpvXfS+uJPmceRbq0aNKHwYe1cJBENA/" +
                        "/2+/F5m+Z/rmN7ElR4tQm1iAc9y0AZVYXKX0FexEwPn//S+Iyu4TwpsG3TdRPTIgGMeRgpTmyTyrS5he5bmT3f4u" +
                        "Cco5Ff2mdHnHPEpIY+fEG045zhGn/wdSTHueS3Tvz2+HizNinCHqE1+b79Vn5TDANGmjn8ULZ104zpN6Duu1xPBag" +
                        "R0iSBIWIBeSKnnvA9nFXr5EwPCFGC/mWbX2Dhx4ik1UYJegQuv5+/l7yjU=");
        this.damage = 18;
        this.cooldown = 142;
        this.range = 15;
        this.damageTypes = new DamageType[]{DamageType.PHYSICAL, DamageType.LIGHT};
    }
}
