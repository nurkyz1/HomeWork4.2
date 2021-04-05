package com.example.ad2l2.ui.onBoard;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ad2l2.R;
import com.example.ad2l2.databinding.LayoutOnboardingBinding;

public class PagerAdapter extends RecyclerView.Adapter<PagerAdapter.OnBoardViewHolder> {
private String [] list = new String[]{"Колезей", "Строительство амфитеатра","Архитектура Колизея"};
private  String [] list1= new String[]{"Колизе́й (лат. Сolosseum), или амфитеатр Флавиев (лат. Amphitheatrum Flavium) — амфитеатр, памятник архитектуры Древнего Рима, наиболее известное и одно из самых грандиозных сооружений Древнего мира, сохранившихся до нашего времени[1]. Находится в Риме, в низине между Эсквилинским, Палатинским и Целиевым холмами.",
        "Постройка амфитеатра была начата императором Веспасианом после его побед в Иудее. Об этом сообщает Светоний[5]:\n" +
                "«\tПредпринял он и новые постройки: …амфитеатр посреди города, задуманный, как он узнал, ещё Августом\t»\n" +
                "Считают, что амфитеатр строился за счёт средств, вырученных от реализации военной добычи[6].\n" +
                "\n" +
                "По оценкам, 100 тысяч заключённых были доставлены в Рим в качестве рабов после войны в Иудее. Рабы использовались для тяжёлых работ, таких как работа в карьерах в Тиволи, где добывался травертин, для подъёма и транспортировки тяжёлых камней на 20 миль от Тиволи до Рима. Команды профессиональных строителей, инженеров, художников и декораторов выполняли ряд задач, необходимых для строительства Колизея[7].\n" +
                "\n",
        "Подобно другим римским амфитеатрам, Амфитеатр Флавиев представляет в плане эллипс, середина которого занята ареной (также эллиптической формы) и окружающими её концентрическими кольцами мест для зрителей. От всех сооружений такого рода Колизей отличается своей величиной. Это самый грандиозный античный амфитеатр: длина его наружного эллипса равняется 524 м, большая ось — 187,77 м, малая ось — 155,64 м, длина арены — 85,75 м, её ширина 53,62 м; высота его стен — от 48 до 50 метров. Конструктивную основу составляют 80 радиально направленных стен и столбов, несущих своды перекрытий. Места для зрителей образуют семь кольцевых уровней — концентрических стен, самая высокая из которых служит внешней стеной здания. "};
private  int [] list2= new int[]{R.drawable.photo,R.drawable.photo_one,R.drawable.photo_two};
    public PagerAdapter() {
    }

    @NonNull
    @Override
    public OnBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new  OnBoardViewHolder(LayoutOnboardingBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OnBoardViewHolder holder, int position) {
        holder.onBind();

    }

    @Override
    public int getItemCount() {

        return 3;
    }


    public    class OnBoardViewHolder extends RecyclerView.ViewHolder{
        private LayoutOnboardingBinding binding;

     public OnBoardViewHolder( @NonNull LayoutOnboardingBinding itemView) {
         super(itemView.getRoot());
         binding=itemView;
     }
     public void  onBind(){
         binding.title.setText(list[getAdapterPosition()]);
         binding.description.setText(list1[getAdapterPosition()]);
         binding.img.setImageResource(list2[getAdapterPosition()]);


         }
     }

 }

