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

package cobalt.ast.variable

import cobalt.ast.Block
import cobalt.ast.empty.EmptyBlock
import cobalt.ast.structures.ObjectMethodCallBlock
import cobalt.symbol_table.{Row, SymbolTable}
import cobalt.utilities.Utils

/**
  * Represents a variable definition
  *
  * @param superBlockInit
  * @param declaration
  * @param name
  * @param varType
  */
class DefineVariableBlock(superBlockInit: Block, declaration: Boolean, name: String, varType: String) extends Block(superBlockInit, false, true) {

  override val getName: String = name

  override val getValue: String = ""

  // Map the defined var type to the ASM type
  override val getType: String = varType

  SymbolTable.getInstance.addRow(new Row().setId(id).setName(getName).setType(getType).setValue(getValue).setMethodName(Utils.getMethod(this).getOrElse(new EmptyBlock()).getName).setClassName(Utils.getClass(this).get.getName))

  override def getOpeningCode: String = {

    // If it is within a method
    if (!Utils.getMethod(this).isEmpty) {

      val code = stack.toList.map(b => b.getOpeningCode + (if(b.isInstanceOf[VariableBlock] || b.isInstanceOf[ObjectMethodCallBlock]) Utils.unwrapCode(b) else "")).mkString("\n")

      // Push the object to the stack
      varType match {
        case "Byte" => "mv.visitTypeInsn(NEW, \"java/lang/Byte\");\n" + "mv.visitInsn(DUP);\n" + code
        case "Short" => "mv.visitTypeInsn(NEW, \"java/lang/Short\");\n" + "mv.visitInsn(DUP);\n" + code
        case "Int" => "mv.visitTypeInsn(NEW, \"java/lang/Integer\");\n" + "mv.visitInsn(DUP);\n"  + code
        case "Long" => "mv.visitTypeInsn(NEW, \"java/lang/Long\");\n" + "mv.visitInsn(DUP);\n" + code
        case "Float" => "mv.visitTypeInsn(NEW, \"java/lang/Float\");\n" + "mv.visitInsn(DUP);\n" + code
        case "Double" => "mv.visitTypeInsn(NEW, \"java/lang/Double\");\n" + "mv.visitInsn(DUP);\n" + code
        case "Char" => "mv.visitTypeInsn(NEW, \"java/lang/Character\");\n" + "mv.visitInsn(DUP);\n" + code
        case "String" =>  code
        case _ => code
      }

    } else {
      "fv = cw.visitField(0, \""+name+"\", \""+Utils.getWrapperType(varType)+"\", null, null);\n" +
        "fv.visitEnd();\n"
    }
  }


  override def getClosingCode: String = {

    // If it is within a method
    if (!Utils.getMethod(this).isEmpty) {
      varType match {
        case "Byte" => "mv.visitMethodInsn(INVOKESPECIAL, \"java/lang/Byte\", \"<init>\", \"(B)V\", false);\n" + "mv.visitVarInsn(ASTORE" + "," + id + ");\n"
        case "Short" => "mv.visitMethodInsn(INVOKESPECIAL, \"java/lang/Short\", \"<init>\", \"(S)V\", false);\n" + "mv.visitVarInsn(ASTORE" + "," + id + ");\n"
        case "Int" => "mv.visitMethodInsn(INVOKESPECIAL, \"java/lang/Integer\", \"<init>\", \"(I)V\", false);\n" + "mv.visitVarInsn(ASTORE" + "," + id + ");\n"
        case "Long" => "mv.visitMethodInsn(INVOKESPECIAL, \"java/lang/Long\", \"<init>\", \"(J)V\", false);\n" + "mv.visitVarInsn(ASTORE" + "," + id + ");\n"
        case "Float" => "mv.visitMethodInsn(INVOKESPECIAL, \"java/lang/Float\", \"<init>\", \"(F)V\", false);\n" + "mv.visitVarInsn(ASTORE" + "," + id + ");\n"
        case "Double" => "mv.visitMethodInsn(INVOKESPECIAL, \"java/lang/Double\", \"<init>\", \"(D)V\", false);\n" + "mv.visitVarInsn(ASTORE" + "," + id + ");\n"
        case "Char" => "mv.visitMethodInsn(INVOKESPECIAL, \"java/lang/Character\", \"<init>\", \"(C)V\", false);\n" + "mv.visitVarInsn(ASTORE" + "," + id + ");\n"
        case _ => "mv.visitVarInsn(ASTORE" + "," + id + ");\n"
      }
    }else{
      ""
    }
  }




  override def toString: String = "def variable: " + name + stack

}