package org.example
import org.jetbrains.kotlinx.multik.api.*
import org.jetbrains.kotlinx.multik.api.linalg.dot
import org.jetbrains.kotlinx.multik.ndarray.complex.ComplexDouble
import org.jetbrains.kotlinx.multik.ndarray.complex.ComplexFloat
import org.jetbrains.kotlinx.multik.ndarray.data.D2Array
import org.jetbrains.kotlinx.multik.ndarray.data.set
import org.jetbrains.kotlinx.multik.ndarray.operations.times
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.time.measureTime

fun getDFTMat(n: Int): D2Array<ComplexDouble> {
    val returnedValue = mk.zeros<ComplexDouble>(n, n)
    for (i in 0 until n) {
        for (j in 0 until n) {
            // expand using Euler
            val a = -2.0*PI*i*j/n.toDouble()
            returnedValue[i, j] = ComplexDouble(cos(a), sin(a))
        }
    }
    return returnedValue
}

fun main() {
    val s = 200
    val f = mk.rand<Double>(s, s)
    val t = measureTime {
        val c = f.dot(f)
    }
    println("matrix multiplication for ${s}x$s matrix is $t")
    val M = getDFTMat(100)
    val signal = mk.zeros<ComplexDouble>(100, 1)
    for (i in 0 until 100) {
        signal[i,0] = ComplexDouble(cos(2.0*PI*i/10), 0)
    }
    val dft = M.dot(signal)
    print(dft)
}