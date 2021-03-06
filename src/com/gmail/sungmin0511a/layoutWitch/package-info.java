/**
 * 
 */
/**
 * {@link Child}들의 위치를 재조정하는 LayoutWitch들입니다.
 * <p>
 * {@link com.gmail.sungmin0511a.drawAbles.Child Child}의 
 * {@link com.gmail.sungmin0511a.drawAbles.Child#draw(java.awt.Graphics2D) draw(Graphics2D)}매서드는 
 * 좌표 0,0에 Child를 그립니다. 
 * 그러나 게임속 케릭터는 어느 한 위치에 고정되어 있지 않고 걷거나, 바닥으로 떨어지고, 
 * 적들을 밀쳐내며 전진해야 할 겁니다. <p>
 * LayoutWitch는 화면에 표시되는 Child가 어디에 표시될지 적당한 장소를 계산하고 배치해 주는 
 * 역할을 합니다. 간단한 물리엔진을 동반하기도 하며, 게임제작을 할때 시간을 크게 단축시켜 
 * 줄 것입니다.
 * @author 신성민
 */
package com.gmail.sungmin0511a.layoutWitch;