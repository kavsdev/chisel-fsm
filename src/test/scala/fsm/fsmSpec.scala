package fsm
import chiseltest._
import chisel3._
import org.scalatest.flatspec.AnyFlatSpec

class fsmSpec extends AnyFlatSpec with ChiselScalatestTester {
  "detect 2 ones" should "pass" in {
    test(new fsm) {dut =>
      dut.io.in.poke(true.B)
      dut.clock.step()
      dut.io.in.poke(false.B)
      dut.clock.step()
      dut.io.in.poke(true.B)
      dut.clock.step()
      dut.io.out.expect(false.B)
      dut.io.in.poke(true.B)
      dut.clock.step()
      dut.io.out.expect(true.B)
      println(s"out= ${dut.io.out.peek().litValue}")
    }
    println("done with test")
  }

}
