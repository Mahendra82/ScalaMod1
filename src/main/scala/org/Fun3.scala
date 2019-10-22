import akka.actor.Actor
import akka.actor.Actor.Receive

object Person {
  case class SetFirstName(name: String)
  case class SetLastName(name: String)
  case class AddRole(role: Role)
}

class Person extends Actor {
  import Person._
  private var firstName: Option[String] = None
  private var lastName: Option[String] = None
  private var roles: Set[Role] = Set.empty

  override def receive:Receive = {
    case SetFirstName(name) => firstName = Some(name)
    case SetLastName(name) => lastName = Some(name)
    case AddRole(role) => roles = roles + role
  }
}

case class Role()