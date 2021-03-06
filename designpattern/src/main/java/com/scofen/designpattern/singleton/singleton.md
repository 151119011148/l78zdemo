单例模式：整个系统只要拥有一个的全局对象

基本的实现思路    
　　单例模式要求类能够有返回对象一个引用(永远是同一个)和一个获得该实例
的方法（必须是静态方法，通常使用getInstance这个名称）。

单例的实现主要是通过以下两个步骤：

　　•将该类的构造方法定义为私有方法，这样其他处的代码就无法通过调用该类的
构造方法来实例化该类的对象，只有通过该类提供的静态方法来得到该类的唯一实例；
　　•在该类内提供一个静态方法，当我们调用这个方法时，如果类持有的引用不为
空就返回这个引用，如果类保持的引用为空就创建该类的实例并将实例的引用赋予
该类保持的引用。

优点     
　　系统内存中该类只存在一个对象，节省了系统资源，对于一些需要频繁创建销毁的
对象，使用单例模式可以提高系统性能。

缺点     
　　当想实例化一个单例类的时候，必须要记住使用相应的获取对象的方法，
而不是使用new，可能会给其他开发人员造成困扰，特别是看不到源码的时候。

适用场合   

    •需要频繁的进行创建和销毁的对象；
    •创建对象时耗时过多或耗费资源过多，但又经常用到的对象；
    •工具类对象；
    •频繁访问数据库或文件的对象。



