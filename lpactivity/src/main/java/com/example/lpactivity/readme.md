本模块是为了测试Activity的生命周期和启动模式
onCreate
onStart
onResume
onPause
onStop
onDestroy

standard:每一次的跳转都会新建一个实例，返回的时候回依次出栈，先进后出:A-B-C-A-B-C   
SingleTask：在栈中，只存在一个实例，如果没有就新建，如果已经存在则顶到栈顶，并他其他的顶出。A-B-C-A-B ----- ABC(AB被顶出)
SingleTop：栈顶复用模式，如果实例在栈顶，则不用重复创建，如果不在栈顶则重新创建：A-B-C-A-B-C /A-B-C-(C)-A-B   
singleInstance：单实例模式，加强的SingleTask模式，只允许一个栈中只有一个实例。因此创建后，如果不是被销毁，那么再此进入

