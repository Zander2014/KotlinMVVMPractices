package pers.zander.mvvmcore.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by zander
 * on 2019/5/31 15:44
 */
abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initView()
//        setSupportActionBar(mToolbar)
        initData()
    }

    abstract fun getLayoutResId(): Int
    abstract fun initView()
    abstract fun initData()

}