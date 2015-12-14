package com.example.android.cyclecdg;

import android.support.v7.app.AppCompatActivity;




        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;




        import java.text.SimpleDateFormat;


        import java.util.Calendar;

        import java.util.Locale;


        import android.app.Activity;
        import android.app.DatePickerDialog;
        import android.app.DatePickerDialog.OnDateSetListener;

        import android.text.InputType;

        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.TextView;





    public class MainActivity extends Activity implements OnClickListener {


        //Champ date choisie par l'user
        private EditText DateEtxt;

        //Date choisie par l'user
        private DatePickerDialog DatePickerDialog;

        //format de la date
        private SimpleDateFormat dateFormatter;


        //date affichée en premier en bas du jour
        private Calendar datePicked = Calendar.getInstance();

        //date de réference pour le calcul du calendrier

        private Calendar dateRef  = Calendar.getInstance();





        private String[] teamDayReference = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};
        private String[] teamDay = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};

        SimpleDateFormat format = new SimpleDateFormat("EEEE d MMMM yyyy", Locale.FRANCE);




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            dateRef.set(2015, 8, 12);


            String datePickedString = format.format(datePicked.getTime());

            String dateRefString = format.format(dateRef.getTime());


            displayDate(datePickedString);
            displayDatePicker(datePickedString);


            dateFormatter = new SimpleDateFormat("EEEE d MMMM yyyy", Locale.FRANCE);

            findViewsById();

            setDateTimeField();


            displayDaysJ(datePicked, dateRef);




        }


        private void findViewsById() {
            DateEtxt = (EditText) findViewById(R.id.etxt_date);
            DateEtxt.setInputType(InputType.TYPE_NULL);
            DateEtxt.requestFocus();
        }


        private void setDateTimeField() {
            DateEtxt.setOnClickListener(this);


            Calendar newCalendar = Calendar.getInstance();
            DatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar newDate = Calendar.getInstance();
                    newDate.set(year, monthOfYear, dayOfMonth);
                    DateEtxt.setText(dateFormatter.format(newDate.getTime()));
                    datePicked.set(newDate.get(Calendar.YEAR), newDate.get(Calendar.MONTH), newDate.get(Calendar.DAY_OF_MONTH));
                }

            },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        }
        @Override
        public void onClick(View view) {

            DatePickerDialog.show();

        }



//Calcule le calendrier des équipes pour la date sélectionnée


        public void submitDate(View view){

            int numberOfDays = daysDifference(datePicked, dateRef);
            int numberOfDaysMod12 = modulo(numberOfDays ,12);

            displayDaysJ(datePicked, dateRef);




        }


        // calcule et affiche le tableau des jours avec en entrée ce qui est la date calculée et la date de référence
        //ensuite affiche le tableau


        private void displayDaysJ(Calendar date, Calendar dateReference){

            int numberOfDays = daysDifference(date, dateReference);
            int numberOfDaysMod12 = modulo(numberOfDays ,12);



            for(int i = 0; i < (12 - numberOfDaysMod12); i++)
            {
                teamDay[i + numberOfDaysMod12]=  teamDayReference[i];
            }

            for(int i = 0 ; i < numberOfDaysMod12; i++)
            {
                teamDay[i]=teamDayReference[12 + i - numberOfDaysMod12];
            }


            displayJ(teamDay[0].toString(), R.id.J1);
            displayJ(teamDay[1].toString(), R.id.J2);
            displayJ(teamDay[2].toString(), R.id.J3);
            displayJ(teamDay[3].toString(), R.id.J4);
            displayJ(teamDay[4].toString(), R.id.J5);
            displayJ(teamDay[5].toString(), R.id.J6);
            displayJ(teamDay[6].toString(), R.id.J7);
            displayJ(teamDay[7].toString(), R.id.J8);
            displayJ(teamDay[8].toString(), R.id.J9);
            displayJ(teamDay[9].toString(), R.id.J10);
            displayJ(teamDay[10].toString(), R.id.J11);
            displayJ(teamDay[11].toString(), R.id.J12);

            String dateString = format.format(date.getTime());
            displayDate(dateString);






        }






// Calcule le nombre de jours d'écarts entre deux dates

        private int daysDifference(Calendar day1, Calendar day2)
        {


            long diff = day1.getTimeInMillis() - day2.getTimeInMillis(); //result in millis


            int  days = (int) (diff / (24 * 60 * 60 * 1000));

            return days;


        }


// calcule le modulo comme sur google, retourne un nombre positif si le nombre est négatif

        private int modulo(int a, int b){
            int c = 0;
            if(a >= 0) {
                c = (a % b);
            }

            else {

                c = (b - ( ((-1)*a) % b ) );

            }

            return c;
        }







        /**
         * Affiche la date choisie en bas.
         */
        private void displayDate(String date) {
            TextView dateTextView = (TextView) findViewById(R.id.date_text_view);
            dateTextView.setText(date);

        }

        /**
         * Affiche la date choisie en bas.
         */
        private void displayDatePicker(String date) {
            TextView datePickerTextView = (TextView) findViewById(R.id.etxt_date);
            datePickerTextView.setText(date);

        }


        /**
         * Affiche le J.
         */
        private void displayJ(String Day, int Id) {
            TextView jTextView = (TextView) findViewById(Id);
            jTextView.setText(Day);

        }
















































    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
