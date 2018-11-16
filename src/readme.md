#mybatis对集合的处理是没有找到的时候不会返回null,
##所以就没有必要进行null处理
#其他的方法就要进行一些非Null的判断；

## 1 **notblank回过滤空格，notempty不会**