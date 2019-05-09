#include <main.h>
#include <math.h>
#include <string.h>
#include <stdlib.h>
#byte  TRISB = 0xf93
#byte  TRISD = 0xf95
#byte  TRISE = 0xf96

#define dizaineAff PIN_E0
#define uniteAff PIN_E1

#use delay (clock = 20000000) 
#use rs232(baud=9600,parity=N,xmit=PIN_C6,rcv=PIN_C7,bits=8)


int volt;
int tens;
int unity;
int value = 0;
int seuil = 0;
#int_rda
void isr() {
    char treshstr[10];
    gets(&treshstr);
    for(int i=0; i<treshstr;i++){
    
     printf(" ok");
    //seuil =  atoi(treshstr);
    }
   disable_interrupts(INT_RDA);

  
}
void afficheur(int nombre){
   
    TRISE = 0;
    TRISD = 0;
    tens =  nombre / 10 % 10;
    unity =  nombre % 10;

   output_d(tens);
   output_high(dizaineAff);
   output_low(uniteAff);
   delay_ms(10);

   output_d(unity);
   output_high(uniteAff);
   output_low(dizaineAff);
   delay_ms(10); 
}

void envoiTemp( int x){
printf("ACT.temp:%d\r\n", x);

}
void maxTemp(int valeur){


   TRISB = 0;
   
   if(valeur <= seuil){
   output_b(10);
   }
   else{
   output_b(01);
    
   }
}

void main(){
   setup_adc_ports(AN0);
   setup_adc(ADC_CLOCK_INTERNAL);
   setup_timer_0(RTCC_INTERNAL|RTCC_DIV_1|RTCC_8_BIT); // 51,2 us overflow
   setup_timer_1(T1_INTERNAL|T1_DIV_BY_1); // 13,1 ms overflow
   enable_interrupts(GLOBAL);
   setup_low_volt_detect(FALSE);
   set_adc_channel(0);
   setup_psp(PSP_DISABLED);
  
   while(TRUE){
     
    delay_ms(10);
    volt = ((float)read_adc() *5 /1023 *100);
    maxTemp(volt);
    afficheur(volt);
   isr();
    if (volt != value) {
         value = volt;
        envoiTemp(volt);
      }
     
   }

}





