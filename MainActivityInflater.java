package com.lab3a.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.lab3a.R;
import com.lab3a.execution.EquationSolver;
import com.lab3a.utils.Permissions;
import com.lab3a.utils.exception.ItersExceededException;

import java.util.Arrays;


public class MainActivityInflater {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void inflate(AppCompatActivity activity) {



        Button button_count = activity.findViewById(R.id.button_count);



        TextView textview_output_result = activity.findViewById(R.id.textview_output_result);



        @SuppressLint("SetTextI18n") View.OnClickListener onButtonCountClick = v -> {
            int counter = 1;
            long t= System.currentTimeMillis();
            long end = t+3000;
            while (System.currentTimeMillis() < end){
                int o = 0; // Начальное значение диапазона - "от"
                int p = 10; // Конечное значение диапазона - "до"
                long a = o + (int) (Math.random() * p);
                long b = o + (int) (Math.random() * p);
                long c = o + (int) (Math.random() * p);
                long d = o + (int) (Math.random() * p);

                long y = o + (int) (Math.random() * p);

                EquationSolver solver = new EquationSolver();

                solver.setCoefficients(new long[] {a, b, c, d});
                solver.setY(y);

                try {
                    solver.solve();
                } catch (ItersExceededException e) {
                    e.printStackTrace();
                }

                long[] roots = solver.getRoots();

                Permissions permissions = new Permissions(roots);

                long[][] perms = permissions.getPerms();

                for (long[] perm : perms) {

                    if (a * perm[0] + b * perm[1] + c * perm[2] + d * perm[3] == y)
                        roots = perm;

                }

                StringBuilder out = new StringBuilder();

                for (int i = 0; i < roots.length; i++) {
                    out.append("X").append(i + 1).append(" = ").append(roots[i]).append("\n");
                }
                counter = counter + 1;







            }
            textview_output_result.setTextColor(activity.getResources().getColor(R.color.red));
            textview_output_result.setText("розв'язано прикладів - "+counter);


        };



        button_count.setOnClickListener(onButtonCountClick);


    }

}
