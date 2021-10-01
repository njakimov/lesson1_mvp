package com.example.lesson1_mvp

import android.os.Bundle
import com.example.lesson1_mvp.databinding.ActivityMainBinding
import com.example.lesson1_mvp.service.MyObserver
import com.example.lesson1_mvp.view.BackButtonListener
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }
    private var vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
        App.instance.appComponent.inject(this)

        vb?.convertJpgToPng?.setOnClickListener {
            val producer = MyObserver.ProducerThread().sendPathToConvert("img.jpg", this)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map { str ->
                    println(Thread.currentThread().name)
                }
                .observeOn(AndroidSchedulers.mainThread())

            MyObserver.ConsumerThread().subscribe(producer)
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backPressed()
    }
}