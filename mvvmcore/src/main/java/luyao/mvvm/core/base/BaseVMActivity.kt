package luyao.mvvm.core.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseVMActivity<VDM : ViewModel, DB : ViewDataBinding> : AppCompatActivity() {

    protected val viewModel by viewModel<VDM>()
    // private val binding by binding<ActivityCollectBinding>(R.layout.activity_collect) 写在外层类
    /**
     *  reified 具体化 kotlin一种增强的泛型使用模式 inline与其配合使用
     *  泛型函数中是无法获取Class对象的 因此需要reified关键字修饰
     *
     * inline修饰函数表示内联函数，会将泛型类型内嵌到编译代码中替换成具体类型，
     * 配合reified关键字使用就可以在泛型函数中获取到反射类型的Class对象
     * */
    protected inline fun <reified T : ViewDataBinding> binding(
        @LayoutRes resId: Int
    ): Lazy<T> = lazy {
        DataBindingUtil.setContentView<T>(this, resId).apply {
            lifecycleOwner = this@BaseVMActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //页面接受的参数方法
        initParam()
        //私有的初始化Databinding和ViewModel方法
        //私有的初始化Databinding和ViewModel方法
        initViewDataBinding(savedInstanceState)
        startObserve()
        initView()
        initData()
    }

    abstract fun initParam()
    abstract fun initView()
    abstract fun initData()
    abstract fun getLayoutResId(): Int

    /**
     * 用来观察ViewModel中的被观察者
     * */
    abstract fun startObserve()

    //        viewModelId = initVariableId();
    //        viewModel = initViewModel();
    //        if (viewModel == null) {
    //            Class modelClass;
    //            Type type = getClass().getGenericSuperclass();
    //            if (type instanceof ParameterizedType) {
    //                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
    //            } else {
    //                //如果没有指定泛型参数，则默认使用BaseViewModel
    //                modelClass = BaseViewModel.class;
    //            }
    //            viewModel = (VM) createViewModel(this, modelClass);
    //        }
    //        //关联ViewModel
    //        binding.setVariable(viewModelId, viewModel);
    //        //支持LiveData绑定xml，数据改变，UI自动会更新
    //        binding.setLifecycleOwner(this);
    //        //让ViewModel拥有View的生命周期感应
    //        getLifecycle().addObserver(viewModel);
    //        //注入RxLifecycle生命周期
    //        viewModel.injectLifecycleProvider(this);
    private fun initViewDataBinding(savedInstanceState: Bundle?) {
        lifecycle.addObserver(viewModel)  //让ViewModel拥有View的生命周期感应
    }
}