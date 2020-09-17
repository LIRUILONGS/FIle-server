package com.liruilong.fileserver.common.aop;

import java.util.Arrays;

/**
 * @Auther: Liruilong
 * @Date: 2020/7/29 17:31
 * @Description:
 */
public enum FileCRUDEnum {
    CREATE("创建",100),
    QUERY("查询",200),
    UPDATE("更新",300),
    DELETE("删除",400),
    OPERATION("操作",500);

    private String name;
    private Integer id;


    FileCRUDEnum(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param id
     * @return java.lang.String
     * @Description  根据Id获取枚举值对应name
     * @Author Liruilong
     * @Date 2020年07月29日  17:07:07
     **/
    public static String valueof(Integer id) {
      return   Arrays.stream(FileCRUDEnum.values())
              .filter(o ->id.equals(o.getId()))
              .findAny()
              .orElse(null).name;
    }
    /**
     * @param id
     * @return com.hhwy.epes.common.aop.FileCRUDEnum
     * @Description  根据Id获取枚举值
     * @Author Liruilong
     * @Date 2020年07月29日  17:07:47
     **/
    public static FileCRUDEnum valueEnum(Integer id) {
        return   Arrays.stream(FileCRUDEnum.values())
                .filter(o ->id.equals(o.getId()))
                .findAny()
                .orElse(null);
    }




}
