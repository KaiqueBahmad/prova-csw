import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { routes } from './app.routes';
import { providePrimeNG } from 'primeng/config';
import Nora from '@primeng/themes/nora';
import { MessageService } from 'primeng/api';
import { provideHttpClient, withFetch } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideAnimationsAsync(),
    providePrimeNG({
      theme: {
          preset: Nora,
          options: {
              prefix: 'p',
              darkModeSelector: 'system',
              cssLayer: false
          }
      },
      ripple: true
    }),
    MessageService,
    provideHttpClient(withFetch())
  ]
};
