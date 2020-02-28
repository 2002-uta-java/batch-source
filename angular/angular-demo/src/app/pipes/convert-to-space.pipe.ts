import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'convertToSpace'
})
export class ConvertToSpacePipe implements PipeTransform {

  transform(value: string, character?: string): string {
    if (value != null) {
      for (let i = 0; i < value.length; ++i) {
        if ((character == undefined && value.charAt(i) == '-') || value.charAt(i) == character) {
          value = value.substring(0, i) + " " + value.substring(i + 1, value.length);
        }
      }
      return value;
    } else {
      return null;
    }
  }

}
