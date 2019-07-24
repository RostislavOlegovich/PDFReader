package com.example.rostislav.pdfreader.core.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.example.rostislav.pdfreader.core.App
import com.example.rostislav.pdfreader.core.mvp.MVPPresenter
import com.example.rostislav.pdfreader.core.mvp.MVPView
import com.example.rostislav.pdfreader.model.presenter.PresenterFactory
import com.example.rostislav.pdfreader.model.presenter.PresenterManager
import java.util.*

abstract class BaseActivity<V : MVPView, P : MVPPresenter<V>> : AppCompatActivity(), MVPView, PresenterFactory<V, P> {
    lateinit var presenter: P
    lateinit var view: V

    private lateinit var presenterManager: PresenterManager
    private lateinit var activityUUID: String

    @LayoutRes
    abstract fun assignLayout(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(assignLayout())

        activityUUID = if (savedInstanceState != null) {
            savedInstanceState.getString(ACTIVITY_UUID)!!
        } else {
            UUID.randomUUID().toString()
        }

        presenterManager = (applicationContext as App).presenterManager
        presenter = presenterManager.getPresenter(activityUUID, this)
        @Suppress("UNCHECKED_CAST")
        view = this as V
        presenter.attach(view)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(ACTIVITY_UUID, activityUUID)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dettach()
    }

    override fun error(exception: Throwable) {
    }

    companion object {
        const val ACTIVITY_UUID = "activity_uuid"
    }
}