package com.example.demo4u.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.math.MathContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    var operatorPressed = false
    var lastIsNumber = false
    var dotUsed = false
    var prefix = ""
    var part1 = 0.0
    var part2 = 0.0
    private var tvOuput  = ""

    fun onDigit(view: View) {
        tvInput.append((view as Button).text)
        lastIsNumber = true
    }

    fun onOperator(view: View) {
    if(lastIsNumber && operatorPressed == false ){
        tvInput.append((view as Button).text)
        operatorPressed = true
        lastIsNumber = false
        dotUsed = false
    }
    else
        Toast.makeText(this,"Add Operand Not Operator",Toast.LENGTH_LONG).show()
    }

    fun onDot(view: View){
        if (lastIsNumber && dotUsed == false){
            tvInput.append((view as Button).text)
            dotUsed = true
            lastIsNumber = false
        }
    }

    fun zeroAfterDot(value: String):String{
        var result = value
        if(value.contains(".0"))
            result = value.substring(0, value.length - 2)
        return result

    }

    fun onEqual(view: View) {

        if (operatorPressed && lastIsNumber) {
            if (tvInput.text.startsWith("-")){
                prefix = "-"
                tvInput.text = tvInput.text.substring(1)
            }
            if (tvInput.text.contains("+")) {
                var splitvalues = (tvInput.text.split("+"))
                var part1 = splitvalues[0]
                var part2 = splitvalues[1]
                if(prefix.isNotEmpty())
                    part1 = prefix + part1
                tvInput.text = zeroAfterDot((part1.toDouble() + part2.toDouble()).toString())
            } else if (tvInput.text.contains("-")) {
                var splitvalues = (tvInput.text.split("-"))
                var part1 = splitvalues[0]
                var part2 = splitvalues[1]
                if(prefix.isNotEmpty())
                    part1 = prefix + part1
                tvInput.text = zeroAfterDot((part1.toDouble() - part2.toDouble()).toString())
            } else if (tvInput.text.contains("*")) {
                var splitvalues = (tvInput.text.split("*"))
                var part1 = splitvalues[0]
                var part2 = splitvalues[1]
                if(prefix.isNotEmpty())
                    part1 = prefix + part1
                tvOuput = (part1.toDouble() * part2.toDouble()).toString()
                tvInput.text = zeroAfterDot(tvOuput)
            } else if (tvInput.text.contains("/")) {
                var splitvalues = (tvInput.text.split("/"))
                var part1 = splitvalues[0]
                var part2 = splitvalues[1]
                if(prefix.isNotEmpty())
                    part1 = prefix + part1
                tvOuput=(part1.toDouble() / part2.toDouble()).toString()
                tvInput.text = zeroAfterDot(tvOuput)

            }
            operatorPressed = false
        }
    }

    fun onClear(view: View) {
        tvInput.text = ""
        dotUsed = false
        lastIsNumber = false
        operatorPressed = false

    }


}
