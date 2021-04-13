    processor 6502

    include "vcs.h"
    include "macro.h"

    seg code
    org $F000       ; defines the origin of the ROM at $F000

START:
    CLEAN_START     ; Macro to safely clear the memory

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;   Set background luminosity color to yellow
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
    lda #$1E        ; Load color into A ($1E is NTSC yellow)
    sta COLUBK      ; Store A to BackgroundColor Address $09

    jmp START       ; Repeat from Start

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;   Fill ROM size to exactly 4KB
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
    org $FFFC       ; Define to origin $FFFC
    .word START     ; reset vector at $FFFC (where the program starts)
    .word START     ; Interrupt vector at $FFFE (unused in VCS)