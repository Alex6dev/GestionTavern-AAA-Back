package aaa.tavern.service.utils;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class RandomService {
    
    Random random= new Random();
    
    public int getRandomInt(int max){
        return random.nextInt(max);
    }

    public long getRandomIntMinMax(int min, int max){
        return random.nextInt(min, max);
    }

    public float getRandomFloat(float max){
        return random.nextFloat(max);
    }

    public Boolean getRandomBoolean(){
        return random.nextBoolean();
    }
}
