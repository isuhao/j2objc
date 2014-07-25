/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.devtools.j2objc.ast;

import java.util.List;

/**
 * Node type for an enum declaration.
 */
public class EnumDeclaration extends AbstractTypeDeclaration {

  private ChildList<EnumConstantDeclaration> enumConstants = ChildList.create(this);

  public EnumDeclaration(org.eclipse.jdt.core.dom.EnumDeclaration jdtNode) {
    super(jdtNode);
    for (Object enumConstant : jdtNode.enumConstants()) {
      enumConstants.add((EnumConstantDeclaration) TreeConverter.convert(enumConstant));
    }
  }

  public EnumDeclaration(EnumDeclaration other) {
    super(other);
    enumConstants.copyFrom(other.getEnumConstants());
  }

  public List<EnumConstantDeclaration> getEnumConstants() {
    return enumConstants;
  }

  @Override
  protected void acceptInner(TreeVisitor visitor) {
    if (visitor.visit(this)) {
      javadoc.accept(visitor);
      annotations.accept(visitor);
      name.accept(visitor);
      enumConstants.accept(visitor);
      bodyDeclarations.accept(visitor);
    }
    visitor.endVisit(this);
  }

  @Override
  public EnumDeclaration copy() {
    return new EnumDeclaration(this);
  }
}