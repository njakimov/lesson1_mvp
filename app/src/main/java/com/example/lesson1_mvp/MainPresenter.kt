package com.example.lesson1_mvp

class MainPresenter(val view: MainView) {
    val model = CountersModel()

    fun counterClick(id: Int) {
        when (id) {
            1 -> {
                val nextValue = model.next(0)
                view.setButtonText1(nextValue.toString())
            }
            2 -> {
                val nextValue = model.next(1)
                view.setButtonText2(nextValue.toString())
            }
            3 -> {
                val nextValue = model.next(2)
                view.setButtonText3(nextValue.toString())
            }
        }
    }
}