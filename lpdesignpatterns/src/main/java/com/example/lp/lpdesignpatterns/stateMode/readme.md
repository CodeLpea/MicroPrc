状态模式：行为模式的一种
应用场景：
对象的行为取决于其状态，随着状态改变时其行为也需改变
包含大量的与状态相关的条件判断语句时

优点：
每个状态都是一个子类，易于扩展和维护。
避免过多的if-else等条件语句，使得结构更清晰，提高代码的可维护性


缺点：
可能会导致状态子类会过多