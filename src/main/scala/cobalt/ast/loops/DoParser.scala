/*
 * Cobalt Programming Language Compiler
 * Copyright (C) 2017  Cobalt
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package cobalt.ast.loops

import cobalt.ast.{Block, Parser}
import cobalt.tokenizer.Tokenizer

/*
need to make parameter single variable names instead as cant define a variable in an ifs...
 */
class DoParser extends Parser[DoBlock] {

  /**
    * A list of all regular stack
    *
    * @return
    */
  override val regex: String = "^do"

  override def parse(superBlock: Block, tokenizer: Tokenizer): DoBlock = {
    new DoBlock(superBlock)
  }
}
