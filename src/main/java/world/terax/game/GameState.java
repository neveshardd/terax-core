package world.terax.game;

public enum GameState {
  AGUARDANDO, INICIANDO, EMJOGO, ENCERRADO;
  
  public boolean canJoin() {
    return this == AGUARDANDO;
  }
}
