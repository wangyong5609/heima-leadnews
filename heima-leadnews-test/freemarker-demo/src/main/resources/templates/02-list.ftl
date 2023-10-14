<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Hello World!</title>
</head>
<body>
<div>
    <p>内置函数：</p>
    <p>
        1. 集合的大小
        ${stus?size}
    </p>
    <p>
        2. 日期格式化
        当前日期：${today?datetime}
        当前日期：${today?string("yyyy年MM月")}
    </p>
    <p>
        3. c,数字转字符串
        ${point} to ${point?c}
    </p>
    <p>
        4. JSON字符串转对象
        <#assign text="{'name':'值'}" />
        <#assign data=text?eval />
        对象值：${data.name}
    </p>
  
</div>

<#-- list 数据的展示 -->
<b>展示list中的stu数据:</b>
<br>
<br>
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
    </tr>
<#--    ?? 判断变量是否存在-->
    <#if stus??>
        <#list stus as stu>
            <#if stu.name='小红' >
                <tr style="color: red">
                    <td>${stu_index + 1}</td>
                    <td>${stu.name}</td>
                    <td>${stu.age}</td>
                    <td>${stu.money}</td>
                </tr>
            <#else >
                <tr>
                    <td>${stu_index + 1}</td>
                    <td>${stu.name}</td>
                    <td>${stu.age}</td>
                    <td>${stu.money}</td>
                </tr>
            </#if>
        </#list>
    </#if>
</table>
<hr>

<#-- Map 数据的展示 -->
<b>map数据的展示：</b>
<br/><br/>
<a href="###">方式一：通过map['keyname'].property</a><br/>
输出stu1的学生信息：<br/>
姓名：${stuMap['stu1'].name}<br/>
年龄：${stuMap['stu1'].age}<br/>
<br/>
<a href="###">方式二：通过map.keyname.property</a><br/>
输出stu2的学生信息：<br/>
姓名：${stuMap.stu2.name}<br/>
年龄：${stuMap.stu2.age}<br/>

<br/>
<a href="###">遍历map中两个学生信息：</a><br/>
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
    </tr>
    <#list stuMap?keys as key>
        <tr>
            <td>${key_index + 1}</td>
            <td>${stuMap[key].name}</td>
            <td>${stuMap[key].age}</td>
            <td>${stuMap[key].money}</td>
        </tr
    </#list>
</table>

</body>
</html>