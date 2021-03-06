package shape.komputation.layers.combination

import shape.komputation.functions.hadamard
import shape.komputation.layers.CombinationLayer
import shape.komputation.matrix.DoubleMatrix

class HadamardCombination(val name: String?) : CombinationLayer(name) {

    private var first = DoubleArray(0)
    private var second = DoubleArray(0)

    override fun forward(first: DoubleMatrix, second: DoubleMatrix): DoubleMatrix {

        this.first = first.entries
        this.second = second.entries

        return DoubleMatrix(first.numberRows, first.numberColumns, hadamard(first.entries, second.entries))

    }

    // d f(x) * g(x) / d f(x) = g(x)
    override fun backwardFirst(chain: DoubleMatrix) =

        DoubleMatrix(chain.numberRows, chain.numberColumns, hadamard(chain.entries, this.second))

    // d f(x) * g(x) / d g(x) = f(x)
    override fun backwardSecond(chain: DoubleMatrix) =

        DoubleMatrix(chain.numberRows, chain.numberColumns, hadamard(chain.entries, this.first))

}