package com.perry.wanandroid.kt.base

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.perry.wanandroid.kt.R

abstract class BaseActivity<VM : BaseViewModel, B : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var viewModel: VM
    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutId)
        initViewModel()
        initBinding()
        startObserve()
        initData()
    }

    abstract val layoutId: Int

    abstract fun providerVmClass(): Class<VM>?

    abstract fun initBinding()

    abstract fun initData()

    open fun startObserve() {
        viewModel.errorMsg.observe(this, Observer { showToast(it) })
    }

    private fun initViewModel() {
        providerVmClass()?.let {
            viewModel = ViewModelProviders.of(this).get(it)
            lifecycle.run { addObserver(viewModel) }
        }
    }

    protected fun startFragment(fragment: Fragment, tag: String = "") {
        if (fragment.isAdded) {
            supportFragmentManager.beginTransaction()
                    .show(fragment)
                    .commit()
        } else {
            supportFragmentManager.beginTransaction()
                    .add(R.id.fl_content, fragment, tag)
                    .commit()
        }
    }

    protected fun startActivity(cls: Class<*>) {
        startActivity(Intent(this, cls))
    }

    protected fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}