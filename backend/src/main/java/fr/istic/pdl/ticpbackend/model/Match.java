package fr.istic.pdl.ticpbackend.model;

public interface Match {
    Equipe getEquipeA();
    Equipe getEquipeB();
    int getScoreA();
    int getScoreB();
    String getLieu();
    void setEquipeA(Equipe teamA);
    void setEquipeB(Equipe teamB);
    void setScoreA(int scoreA);
    void setScoreB(int scoreB);
    void setLieu(String lieu);
}
