package algebra
package lattice

import scala.{specialized => sp}

/**
 * A lattice is a set `A` together with two operations (meet and
 * join). Both operations individually constitute semilattices (join-
 * and meet-semilattices respectively): each operation is commutative,
 * associative, and idempotent.
 * 
 * The join and meet operations are also linked by absorption laws:
 * 
 *   meet(a, join(a, b)) = join(a, meet(a, b)) = a
 * 
 * Additionally, join and meet distribute:
 * 
 *   - a ∨ (b ∧ c) = (a ∨ b) ∧ (a ∨ c)
 *   - a ∧ (b ∨ c) = (a ∧ b) ∨ (a ∧ c)
 *
 * Join can be thought of as finding a least upper bound (supremum),
 * and meet can be thought of as finding a greatest lower bound
 * (infimum).
 * 
 * In many texts the following symbols are used:
 * 
 *   - ∧ for meet
 *   - ∨ for join
 */
trait Lattice[@sp(Boolean, Byte, Short, Int, Long, Float, Double) A] extends Any with JoinSemilattice[A] with MeetSemilattice[A]

trait LatticeFunctions {
  def join[@sp(Boolean, Byte, Short, Int, Long, Float, Double) A](x: A, y: A)(implicit ev: JoinSemilattice[A]): A =
    ev.join(x, y)

  def meet[@sp(Boolean, Byte, Short, Int, Long, Float, Double) A](x: A, y: A)(implicit ev: MeetSemilattice[A]): A =
    ev.meet(x, y)
}

object Lattice extends LatticeFunctions {

  /**
   * Access an implicit `Lattice[A]`.
   */
  @inline final def apply[@sp(Boolean, Byte, Short, Int, Long, Float, Double) A](implicit ev: Lattice[A]): Lattice[A] = ev
}
