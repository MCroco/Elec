#include <main.h>
#include <math.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#byte  TRISB = 0xf93
#byte  TRISD = 0xf95
#byte  TRISE = 0xf96
#define dizaineAff PIN_E0
#define uniteAff PIN_E1
#define GREEN_LED PIN_B1
#define RED_LED PIN_B0
#use delay (clock = 20000000) 
#use rs232(baud=9600,parity=N,xmit=PIN_C6,rcv=PIN_C7,bits=8)

int volt;
int tens;
int unity;
int value = 0 ;
int b = 1000;
int i = 0;
char treshstr[3];

boolean flag = 0 ;

#int_rda
void isr() {
      
      while(flag == 0 ){
        
         treshstr[i] = getc();
              disable_interrupts(INT_RDA);
          i++;
          if(i == 3){
          flag = 1;
           i = 0;
         
          }
      }
   }

void afficheur(int nombre){
     
    TRISE = 0;
    TRISD = 0;
    tens =  nombre / 10 % 10;
    unity =  nombre % 10;

   output_d(tens);
   output_high(dizaineAff);
   output_low(uniteAff);
   delay_ms(100);

   output_d(unity);
   output_high(uniteAff);
   output_low(dizaineAff);
   delay_ms(100); 
   
}

void traduction(){
 volt =((float)read_adc() *5 /1023 *100);
     afficheur(volt);
}

void envoiTemp( int x){
printf("%d", x);

}
   void maxTemp(volt,b){
   
    TRISB = 0;
   
   if (volt <  b) {
    
    output_low(RED_LED);
     delay_ms(300);
    output_high(GREEN_LED);
    
    
   }
      
     else {
   
    output_high(RED_LED);
    delay_ms(300);
     output_low(RED_LED);
    output_low(GREEN_LED);
     
   }

}

void main(){
   setup_adc_ports(AN0);
   set_adc_channel(0); 
   setup_adc(ADC_CLOCK_INTERNAL);
   setup_low_volt_detect(FALSE);

   while(TRUE){
     enable_interrupts(INT_RDA);
     enable_interrupts(GLOBAL);
     
     
     if (flag) {
     flag = 0;
     b = ((float)(treshstr[1] - 48)*10 + (treshstr[2] -48));

     //printf("%d",b);
     }
 
     traduction();
     maxTemp(volt, b);
    
    if (volt != value) {
         value = volt;
        envoiTemp(volt);
    }
    
     
   }
}

