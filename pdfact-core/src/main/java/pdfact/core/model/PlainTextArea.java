package pdfact.core.model;

import com.google.inject.assistedinject.AssistedInject;

import pdfact.core.util.list.CharacterList;
import pdfact.core.util.list.CharacterList.CharacterListFactory;

/**
 * A plain implementation of {@link TextArea}.
 * 
 * @author Claudius Korzen
 */
public class PlainTextArea implements TextArea {
  /**
   * The characters of this text area.
   */
  protected CharacterList characters;

  /**
   * The position of this text area.
   */
  protected Position position;

  /**
   * The statistic about the characters in this area.
   */
  protected CharacterStatistic characterStatistic;

  /**
   * The default constructor.
   * 
   * @param characterListFactory
   *        The factory to create instances of {@link CharacterList}.
   */
  @AssistedInject
  public PlainTextArea(CharacterListFactory characterListFactory) {
    this.characters = characterListFactory.create();
  }

  // ==========================================================================

  @Override
  public CharacterList getCharacters() {
    return this.characters;
  }

  @Override
  public Character getFirstCharacter() {
    if (this.characters == null || this.characters.isEmpty()) {
      return null;
    }
    return this.characters.get(0);
  }

  @Override
  public Character getLastCharacter() {
    if (this.characters == null || this.characters.isEmpty()) {
      return null;
    }
    return this.characters.get(this.characters.size() - 1);
  }

  @Override
  public void setCharacters(CharacterList characters) {
    this.characters = characters;
  }

  @Override
  public void addCharacters(CharacterList characters) {
    this.characters.addAll(characters);
  }

  @Override
  public void addCharacter(Character character) {
    this.characters.add(character);
  }

  // ==========================================================================

  @Override
  public CharacterStatistic getCharacterStatistic() {
    return this.characterStatistic;
  }

  @Override
  public void setCharacterStatistic(CharacterStatistic statistic) {
    this.characterStatistic = statistic;
  }

  // ==========================================================================

  @Override
  public Position getPosition() {
    return this.position;
  }

  @Override
  public void setPosition(Position position) {
    this.position = position;
  }
}
