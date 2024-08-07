package fsm

import chisel3._
import _root_.circt.stage.ChiselStage
import chisel3.util._




class fsm extends Module{
  val io= IO(new Bundle{
    val in = Input(Bool())
    val out = Output(Bool())
  })
  object State extends ChiselEnum {
    val sNone, sOne1, sTwo1 = Value
  }

  val state = RegInit(State.sNone)

  io.out:= (state === State.sTwo1)
  switch(state){
    is(State.sNone){
      when(io.in){
        state:=State.sOne1
      }
    }
    is(State.sOne1){
      when(io.in){
        state:=State.sTwo1
      }.otherwise{
        state:=State.sNone
      }
    }
    is(State.sTwo1){
      when(!io.in){
        state:=State.sNone
      }
    }
  }
}


object fsm extends App {
  ChiselStage.emitSystemVerilogFile(
    new fsm,
    firtoolOpts = Array("-disable-all-randomization", "-strip-debug-info")
  )
  ChiselStage.emitFIRRTLDialect(new fsm, firtoolOpts = Array("-disable-all-randomization"))
}