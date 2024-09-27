package org.factoriaf5.digital_academy.funko_shop.discount;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountDTO {

  private Long id;
  private float percentage;
  private boolean isActive;

}
