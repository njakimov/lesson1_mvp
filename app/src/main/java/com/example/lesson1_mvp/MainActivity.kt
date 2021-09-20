package com.example.lesson1_mvp

import android.os.Bundle
import com.example.lesson1_mvp.databinding.ActivityMainBinding
import com.example.lesson1_mvp.screens.AndroidScreens
import com.example.lesson1_mvp.service.MyObserver
import com.example.lesson1_mvp.view.BackButtonListener
import com.github.terrakok.cicerone.androidx.AppNavigator
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter { MainPresenter(App.instance.router, AndroidScreens) }
    private var vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        // тут я не понял, надо ли было делать получение через диалоговое окно файла на конвератцию и
        // его дальнейший вывод на экран.
        // Сделал быстрый вариант - чтение с ассетов, запись в галерею,
        // Хотя если делать полноценное приложение, то надо сделать открытие окна выбора файла, считывание файла с директории, отображение картинки сверху, отдельно кнопка конвертировать и отдельно кнопка сохранить
        // т.к. я не знаю Ваших требований, то выполняю задачу как понял я - в объеме, показывающим факт, что я разобрался по теме.
        // по идеи новое название файла надо делать при выборе файла на конвертацию. но собсно  - пусть остается как есть.

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
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
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