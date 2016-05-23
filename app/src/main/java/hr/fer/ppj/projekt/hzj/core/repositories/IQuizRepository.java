package hr.fer.ppj.projekt.hzj.core.repositories;

import java.util.List;

import hr.fer.ppj.projekt.hzj.core.models.Quiz;

/**
 * Created by ANTE on 21.5.2016..
 */
public interface IQuizRepository extends IHZJRepository<Quiz> {
    List<Quiz> getByHardness(int dbID);
}
