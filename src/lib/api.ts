// import { keycloak } from './keycloak';

// export async function fetchSecured(path: string, options?: RequestInit): Promise<Response> {
//     const kc = keycloak;
    
//     if (!kc.authenticated) {
//         throw new Error("Brukeren er ikke logget inn. Kan ikke kalle sikret API.");
//     }
//     try {
//         await kc.updateToken(5); 
//         const response = await fetch(`${path}`, { 
//             ...options,
//             headers: {
//                 ...options?.headers,
//                 'Authorization': `Bearer ${kc.token}`, 
//                 'Content-Type': 'application/json'
//             },
//         });
//         return response;
        
//     } catch (error) {
//         console.error("Feil ved API-kall eller token-oppdatering:", error);
//         if (kc.authenticated) {
//             kc.login();
//         }
//         throw error;
//     }
// }