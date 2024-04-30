package com.blog.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data  // 这个注释不能删，会报错：No serializer found for class com.blog.result.PageResult and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) (through reference chain: com.blog.result.Result["data"])
// 我的理解就是当Controller中返回响应的时候，会进行序列化：将java对象序列化成json字符串，如果没有@Data，就没有set、get方法，这样的话就无法获取java对象的属性值，就会报错
// 虽然PageResult没有implements Serializable，但是似乎依然能够序列化，那么苍穹外卖中的implements Serializable到底有什么作用呢
@AllArgsConstructor
@NoArgsConstructor
public class PageResult implements Serializable {
    private List list;
    private Long total;
    private Integer current;
    private Integer size;
}
