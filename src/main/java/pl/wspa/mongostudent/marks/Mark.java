package pl.wspa.mongostudent.marks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mark
{
    
    private String shopName;
    private List<Mark> Marks = new ArrayList<>();
    
    public void addMark(Mark Mark)
    {
        Marks.add(Mark);
    }
}
