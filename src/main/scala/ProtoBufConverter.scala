import org.mixql.protobuf.messages.clientMsgs._

import scala.util.Try

object ProtoBufConverter {
  def unpackAnyMsg(array: scala.Array[Byte]): scalapb.GeneratedMessage = {
    try {
      val anyMsg = AnyMsg.parseFrom(array)
      anyMsg.`type` match {
        case "org.mixql.protobuf.messages.clientMsgs.EngineName" => anyMsg.getMsg.unpack[EngineName]
        case "org.mixql.protobuf.messages.clientMsgs.ShutDown" => anyMsg.getMsg.unpack[ShutDown]
        case "org.mixql.protobuf.messages.clientMsgs.Execute" => anyMsg.getMsg.unpack[Execute]
        case "org.mixql.protobuf.messages.clientMsgs.Param" => anyMsg.getMsg.unpack[Param]
        case "org.mixql.protobuf.messages.clientMsgs.Error" => anyMsg.getMsg.unpack[Error]
        case _: Any => Error(s"Protobuf anymsg converter: Error: Got unknown type ${anyMsg.`type`} of message")
      }
    } catch {
      case e: Throwable => Error(s"Protobuf anymsg converter: Error: " + e.getMessage)
    }
  }

  def toType(protobufType: java.lang.String, anyMsg: com.google.protobuf.any.Any): scalapb.GeneratedMessage = {
    try {
      protobufType match {
        case "org.mixql.protobuf.messages.clientMsgs.NULL" => anyMsg.unpack[NULL]
        case "org.mixql.protobuf.messages.clientMsgs.Bool" => anyMsg.unpack[Bool]
        case "org.mixql.protobuf.messages.clientMsgs.Int" => anyMsg.unpack[Int]
        case "org.mixql.protobuf.messages.clientMsgs.Double" => anyMsg.unpack[Double]
        case "org.mixql.protobuf.messages.clientMsgs.String" => anyMsg.unpack[String]
        case "org.mixql.protobuf.messages.clientMsgs.Array" => anyMsg.unpack[Array]
        case _: Any => Error(s"Protobuf type converter: Error: Got unknown type ${protobufType}")
      }
    } catch {
      case e: Throwable => Error(s"Protobuf type converter: Error: " + e.getMessage)
    }
  }

  def toArray(msg: scalapb.GeneratedMessage): Try[scala.Array[Byte]] = {
    Try{
      AnyMsg(
        msg.getClass.getName,
        Some(com.google.protobuf.any.Any.pack(msg))
      ).toByteArray
    }
  }
}
