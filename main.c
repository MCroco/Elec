
#include <18F458.h>
#include <ADS8320.C>
#include <math.h>

#byte  TRISB = 0xf93
#byte  TRISD = 0xf95

#byte  TRISE = 0xf96



  

#define dizaineAff PIN_E0
#define uniteAff PIN_E1

 #use delay (clock = 20000000) 



void afficheur(int nombre){
   
   int tens = nombre / 10 % 10;
   int unity = nombre % 10;
   
   output_d(tens);
   output_high(dizaineAff);
   output_low(uniteAff);
   delay_ms(10);

   output_d(unity);
   output_high(uniteAff);
   output_low(dizaineAff);
   delay_ms(10);
  
}
void maxTemp(int valeur){

   int valMax = 28;
   TRISB = 0;
   
   if(valeur <= valMax){
   output_b(10);
   }
   else{
   output_b(01);
   
    
   }
}

void main() {
 setup_adc_ports(ALL_ANALOG);
   init_ext_adc();


   set_adc_channel(0);
   setup_adc(ADC_CLOCK_INTERNAL);
   setup_low_volt_detect(FALSE);
    enable_interrupts(GLOBAL);
   
   TRISD = 0;
   TRISE = 0;
  
   int volt;
  int temperature;
   while(true){

      delay_ms(10);
      volt  = read_adc()*(1.1 /1023) * ( 100.0 );
     
      temperature = volt *(100/255);
       
      maxTemp(temperature);
      
      afficheur(temperature);
      
   



   }
}

