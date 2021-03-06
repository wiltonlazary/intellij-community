package com.intellij.lang.properties.xml;

import com.intellij.lang.properties.IProperty;
import com.intellij.lang.properties.psi.PropertiesFile;
import com.intellij.pom.PomRenameableTarget;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import com.intellij.psi.PsiTarget;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.PlatformIcons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author Dmitry Avdeev
 *         Date: 7/26/11
 */
public class XmlProperty implements IProperty, PomRenameableTarget, PsiTarget {

  @NotNull
  private final XmlTag myTag;
  private final XmlPropertiesFile myPropertiesFile;

  public XmlProperty(@NotNull XmlTag tag, XmlPropertiesFile xmlPropertiesFile) {
    myTag = tag;
    myPropertiesFile = xmlPropertiesFile;
  }

  @Override
  public String getName() {
    return myTag.getAttributeValue("key");
  }

  @Override
  public boolean isWritable() {
    return myTag.isWritable();
  }

  @Override
  public PsiElement setName(@NotNull String name) {
    return myTag.setAttribute("key", name);
  }

  @Override
  public String getKey() {
    return getName();
  }

  @Override
  public String getValue() {
    return myTag.getValue().getText();
  }

  @Override
  public String getUnescapedValue() {
    return getValue();
  }

  @Override
  public String getUnescapedKey() {
    return getKey();
  }

  @Override
  public void setValue(@NonNls @NotNull String value) throws IncorrectOperationException {
    myTag.getValue().setText(value);
  }

  @Override
  public PropertiesFile getPropertiesFile() throws PsiInvalidElementAccessException {
    return myPropertiesFile;
  }

  @Override
  public String getDocCommentText() {
    return null;
  }

  @NotNull
  @Override
  public PsiElement getPsiElement() {
    return myTag;
  }

  @Override
  public void navigate(boolean requestFocus) {

  }

  @Override
  public boolean canNavigate() {
    return true;
  }

  @Override
  public boolean canNavigateToSource() {
    return true;
  }

  @Override
  public Icon getIcon(int flags) {
    return PlatformIcons.PROPERTY_ICON;
  }

  @Override
  public boolean isValid() {
    return myTag.isValid();
  }

  @NotNull
  @Override
  public PsiElement getNavigationElement() {
    return getPsiElement();
  }
}
